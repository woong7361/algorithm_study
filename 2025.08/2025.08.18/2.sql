
-- https://school.programmers.co.kr/learn/courses/30/lessons/301646
-- start 30

-- 2번 형질 X & 1 or 3번 형질을 가지고있는 대장군 개채를 count
SELECT
    SUM(CASE
        WHEN (GENOTYPE & b'10' = b'00') AND ((GENOTYPE & b'1' = b'1') OR (GENOTYPE & b'100' = b'100')) THEN 1
        ELSE 0
    END) AS COUNT
FROM ECOLI_DATA AS e
