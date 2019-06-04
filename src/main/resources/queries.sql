-- find all limit exceeded requests
SELECT ip
FROM requests
WHERE date BETWEEN '2017-01-01.13:00:00' AND '2017-01-01.13:00:00' + INTERVAL 1 HOUR
GROUP BY ip
HAVING COUNT(1) >= 200;

-- check is particular ip is exceeded request limits
SELECT EXISTS(
  SELECT 1
  FROM requests
  WHERE ip = '192.168.228.188'
  HAVING COUNT(1) > 200
) AS is_exceeded
FROM DUAL;