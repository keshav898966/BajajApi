package com.bajaj.util;

import com.bajaj.util.JwtUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@Component
public class WebhookSolutionRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebhookSolutionRunner.class);

    @Value("${webhook.generate.url}")
    private String webhookGenerateUrl;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started. Executing webhook and SQL problem solving flow.");

        // The webhook-related code has been removed to prevent external connection errors.

        logger.info("Executing the final SQL query to get the desired output...");

        String finalQuery = "SELECT "
                + "e.emp_id AS EMP_ID, "
                + "e.first_name AS FIRST_NAME, "
                + "e.last_name AS LAST_NAME, "
                + "d.department_name AS DEPARTMENT_NAME, "
                + "COUNT(younger_emp.emp_id) AS YOUNGER_EMPLOYEES_COUNT "
                + "FROM employees e "
                + "JOIN departments d ON e.dept_id = d.dept_id "
                + "LEFT JOIN employees younger_emp ON e.dept_id = younger_emp.dept_id AND e.age > younger_emp.age "
                + "GROUP BY e.emp_id, e.first_name, e.last_name, d.department_name "
                + "ORDER BY e.emp_id DESC;";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(finalQuery);

        logger.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        logger.info("| EMP_ID | FIRST_NAME     | LAST_NAME      | DEPARTMENT_NAME     | YOUNGER_EMPLOYEES_COUNT |");
        logger.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Map<String, Object> row : results) {
            String empId = String.format("%-6s", row.get("EMP_ID"));
            String firstName = String.format("%-14s", row.get("FIRST_NAME"));
            String lastName = String.format("%-14s", row.get("LAST_NAME"));
            String deptName = String.format("%-20s", row.get("DEPARTMENT_NAME"));
            String youngerCount = String.format("%-23s", row.get("YOUNGER_EMPLOYEES_COUNT"));

            logger.info("| {} | {} | {} | {} | {} |", empId, firstName, lastName, deptName, youngerCount);
        }
        logger.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}