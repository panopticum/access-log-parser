# Installation instruction

* clone the repository `git clone https://github.com/panopticum/access-log-parser`
* execute sql statements from the file access-log-parser/src/main/resources/schemas.sql
* configure the database credentials in access-log-parser/src/main/resources/application.properties
* build the project `mvn package`
* execute: `./access-log-parser/target/parser.jar --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200`

# Find all limit exceeded requests
SELECT ip
FROM requests
WHERE date BETWEEN '2017-01-01.13:00:00' AND '2017-01-01.13:00:00' + INTERVAL 1 HOUR
GROUP BY ip
HAVING COUNT(1) >= 200;

# Check is particular ip is exceeded request limits
SELECT EXISTS(
  SELECT 1
  FROM requests
  WHERE ip = '192.168.228.188'
  HAVING COUNT(1) > 200
) AS is_exceeded
FROM DUAL;