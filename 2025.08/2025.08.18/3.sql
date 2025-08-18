
-- https://school.programmers.co.kr/learn/courses/30/lessons/298517
-- start 30

-- 가장 큰 물고기 10마리의 ID 출력
-- 결과는 길이를 기준으로 내림차순 정렬
-- 길이가 같다면 물고기 ID에 대해 오름차순 정렬

SELECT ID, LENGTH
FROM FISH_INFO AS FI
ORDER BY LENGTH DESC, ID ASC
LIMIT 10
