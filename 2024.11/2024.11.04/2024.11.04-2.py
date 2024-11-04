# 백준 1987 알파벳 https://www.acmicpc.net/problem/1987
import collections
import pprint
import sys

# sys.setrecursionlimit(6000000)

INF = 2147483647


def input(): return sys.stdin.readline().rstrip()


def splitInput(): return list(map(int, input().split(" ")))


R, C = splitInput()

board = []
for _ in range(0, R):
    board.append(list(input()))

visit = [0] * 26
result = [0]


def dfs(row, col):
    # result[0] = max(result[0], sum(visit))

    for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        row_dx = row + dx
        col_dy = col + dy
        if row_dx < 0 or row_dx >= R or col_dy < 0 or col_dy >= C:
            continue
        if visit[ord(board[row_dx][col_dy])-65] == 1:
            result[0] = max(result[0], sum(visit))
            continue

        visit[ord(board[row_dx][col_dy])-65] = 1
        dfs(row_dx, col_dy)
        visit[ord(board[row_dx][col_dy])-65] = 0


visit[ord(board[0][0])-65] = 1
dfs(0, 0)

print(result)

