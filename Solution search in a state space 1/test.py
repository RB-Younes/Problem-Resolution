import sys

import pygame as py
from collections import deque as queue

PC = (255, 215, 0)
BLACK = (108, 98, 113)
WHITE = (250, 250, 250)
BGC = (148, 137, 153)
GREEN = (65, 83, 44)
BC = (198, 192, 199)
BLUE = (113, 145, 160)
RED = (235, 120, 104)
blockSize = 40
Xmax = 0
Ymax = 0
Xtarget = 0
Ytarget = 0
Xmv = 0
Ymv = 0
End = False
obstacles = []
visited = []
fathers = []
s = []


def addObstacle(X, Y, color):
    obstacles.append((X, Y, color))


def Main():
    global SCREEN, CLOCK, WINDOW_WIDTH, WINDOW_HEIGHT, Xmv, Ymv, End
    WINDOW_WIDTH = Ymax * 40
    WINDOW_HEIGHT = Xmax * 40
    py.init()
    SCREEN = py.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
    CLOCK = py.time.Clock()
    SCREEN.fill(BGC)

    drawGrid()
    createTarget()
    createObstacles()
    colorblock(Xmv, Ymv, BLACK)
    py.display.update()

    while True:
        if not End:
            End = BFS()
            path()
            print(visited)

        for event in py.event.get():
            if event.type == py.QUIT:
                py.quit()
                sys.exit()


def drawGrid():
    for x in range(0, WINDOW_HEIGHT, blockSize):
        for y in range(0, WINDOW_WIDTH, blockSize):
            rectan = py.Rect(y, x, blockSize, blockSize)
            py.draw.rect(SCREEN, BLACK, rectan, 1)


def colorblock(X, Y, COLOR):
    Xstep = X * 40
    Ystep = Y * 40
    for x in range(0, WINDOW_HEIGHT, blockSize):
        for y in range(0, WINDOW_WIDTH, blockSize):
            if Xstep == x and Ystep == y:
                rectan = py.Rect(y, x, 39, 39)
                py.draw.ellipse(SCREEN, COLOR, rectan)
                if COLOR in [WHITE, RED, BLUE, GREEN]:
                    py.draw.ellipse(SCREEN, BLACK, rectan, 1)


def discolorblock(X, Y):
    Xstep = X * blockSize
    Ystep = Y * blockSize
    for x in range(0, WINDOW_HEIGHT, blockSize):
        for y in range(0, WINDOW_WIDTH, blockSize):
            if Xstep == x and Ystep == y:
                rectan = py.Rect(y, x, 39, 39)
                py.draw.ellipse(SCREEN, BGC, rectan)
                py.draw.ellipse(SCREEN, BLACK, rectan, 1)


def createTarget():
    colorblock(Xtarget, Ytarget, WHITE)


def createObstacles():
    for i in range(len(obstacles)):
        X, Y, selectedcolor = obstacles[i]
        colorblock(X, Y, selectedcolor)


def path():
    (x, y) = (Xtarget, Ytarget)
    colorblock(x, y, PC)
    py.time.delay(100)
    py.display.update()
    while (x, y) != (Xmv, Ymv):
        index = visited.index((x, y))
        x, y = fathers[index]
        colorblock(x, y, PC)
        py.time.delay(100)
        py.display.update()


def p():
    i = len(s) - 1
    (x, y) = (Xtarget, Ytarget)
    colorblock(x, y, PC)
    py.time.delay(100)
    py.display.update()
    while i != 0:
        x, y = s[i]
        colorblock(x, y, PC)
        py.time.delay(100)
        py.display.update()


def Visited(X, Y):
    if (X, Y) in visited:
        return True
    return False


def isValid(X, Y):
    if X < 0 or Y < 0 or X >= Xmax or Y >= Ymax:
        return False

    for i in range(len(obstacles)):
        x, y, color = obstacles[i]
        if (x, y) == (X, Y):
            return False

    return True


# Function to perform the BFS traversal
def BFS():

    q = queue()

    q.append((Xmv, Ymv))
    visited.append((Xmv, Ymv))
    fathers.append((Xmv, Ymv))

    while len(q) > 0:
        x, y = q.popleft()

        colorblock(x, y, BC)
        py.time.delay(100)
        py.display.update()

        if (x, y) == (Xtarget, Ytarget):
            return True

        adjy = y
        adjx = x - 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            q.append((adjx, adjy))
            visited.append((adjx, adjy))
            fathers.append((x, y))
        adjx = x + 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            q.append((adjx, adjy))
            visited.append((adjx, adjy))
            fathers.append((x, y))
        adjx = x
        adjy = y - 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            q.append((adjx, adjy))
            visited.append((adjx, adjy))
            fathers.append((x, y))
        adjy = y + 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            q.append((adjx, adjy))
            visited.append((adjx, adjy))
            fathers.append((x, y))


def PathDFS():
    x, y = visited[len(visited) - 1]
    i = len(visited) - 1
    colorblock(x, y, PC)
    py.time.delay(100)
    py.display.update()
    while (x, y) != (Xmv, Ymv) and i >= 0:
        if i < len(visited) - 1:
            xnext, ynext = visited[i]
            if (xnext, ynext) == (x + 1, y) or (xnext, ynext) == (x, y + 1) or (x - 1, y) == (xnext, ynext) or (
                    xnext, ynext) == (x, y - 1):
                colorblock(x, y, PC)
                py.time.delay(100)
                py.display.update()
                (x, y) = visited[i]

            i -= 1
        if i == len(visited) - 1:
            xnext, ynext = visited[i - 1]
            if (xnext, ynext) == (x + 1, y) or (xnext, ynext) == (x, y + 1) or (x - 1, y) == (xnext, ynext) or (
                    xnext, ynext) == (x, y - 1):
                colorblock(x, y, PC)
                py.time.delay(100)
                py.display.update()
                (x, y) = visited[i]
            i -= 1
    colorblock(x, y, PC)
    py.time.delay(100)
    py.display.update()


def DFS():
    st = []
    st.append([Xmv, Ymv])
    fathers.append((Xmv, Ymv))

    while (len(st) > 0):
        # Pop the top pair

        x, y = st[len(st) - 1]
        st.remove(st[len(st) - 1])

        if not Visited(x, y):
            visited.append((x, y))
            colorblock(x, y, BC)
            py.time.delay(100)
            py.display.update()
        if (x, y) == (Xtarget, Ytarget):
            return True
        adjy = y
        adjx = x - 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            st.append([adjx, adjy])
        adjx = x + 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            st.append([adjx, adjy])
        adjx = x
        adjy = y - 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            st.append([adjx, adjy])
        adjy = y + 1
        if isValid(adjx, adjy) and not Visited(adjx, adjy):
            st.append([adjx, adjy])


if __name__ == '__main__':
    Ymax = 9
    Xmax = 9

    Xmv = 0
    Ymv = 0

    Xtarget = 8
    Ytarget = 8

    addObstacle(0, 1, RED)
    addObstacle(1, 1, BLUE)
    addObstacle(2, 1, GREEN)
    addObstacle(3, 1, RED)

    addObstacle(0, 6, RED)
    addObstacle(1, 6, BLUE)
    addObstacle(2, 6, GREEN)
    addObstacle(3, 6, RED)

    addObstacle(5, 6, RED)
    addObstacle(6, 6, BLUE)
    addObstacle(7, 6, GREEN)
    addObstacle(7, 7, RED)
    addObstacle(7, 8, BLUE)

    addObstacle(3, 8, RED)

    addObstacle(0, 4, RED)
    addObstacle(1, 4, BLUE)
    addObstacle(2, 4, GREEN)

    addObstacle(5, 4, RED)
    addObstacle(6, 4, BLUE)
    addObstacle(7, 4, GREEN)
    addObstacle(8, 4, RED)

    addObstacle(4, 3, RED)
    addObstacle(5, 3, RED)
    addObstacle(6, 3, BLUE)
    addObstacle(7, 3, GREEN)
    addObstacle(8, 3, RED)
    "test"
    Main()
