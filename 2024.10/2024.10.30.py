# 백준 30804 https://www.acmicpc.net/problem/30804
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647

def input(): return sys.stdin.readline().rstrip()
def splitInput(): return list(map(int, input().split(" ")))


N = input()

numbers = splitInput()

result = -10e10

start = 0
end = 0

numberCounter = [0] * 10
numberSet = set()

while end < len(numbers):
    nextNumber = numbers[end]
    # result = max(end - start, result)

    # 앞으로는 가야하니까 end 는 무조건 추가
    numberSet.add(nextNumber)
    numberCounter[nextNumber] += 1

    # 대신 set > 2 이면 start를 조정하여 빼는 방식으로 조정
    if len(numberSet) > 2:
        while len(numberSet) > 2:
            startNumber = numbers[start]
            numberCounter[startNumber] -= 1

            if numberCounter[startNumber] == 0:
                numberSet.remove(startNumber)

            start += 1

    result = max(end-start+1, result)

    end += 1

print(result)
