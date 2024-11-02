# 백준 15663 N과M (9) https://www.acmicpc.net/problem/15663
import collections
import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


N, M = splitInput()

numbers = splitInput()
# numbers.sort()
numbersSet = sorted(list(set(numbers)))

def dfs(result):
    if len(result) == M:
        print(" ".join(map(str, result)))
        return

    for number in numbersSet:
        # number의 개수가 result에 있는 수보다 더 클때 추가 가능
        if numbers.count(number) > result.count(number):
            result.append(number)
            dfs(result)
            result.pop()


for number in numbersSet:
    dfs([number])

