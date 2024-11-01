# 백준 15652 N과M (4) https://www.acmicpc.net/problem/15652
import collections
import pprint
import sys

sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


N, M = splitInput()

numbers = splitInput()
numbers.sort()


def dfs(result):
    if len(result) == M:
        print(" ".join(map(str, result)))
        return

    for number in numbers:
        if number not in result:
            result.append(number)
            dfs(result)
            result.pop()


for number in numbers:
    dfs([number])


