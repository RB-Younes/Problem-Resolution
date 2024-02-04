import random
import sys
import tkinter as tk

import pygame as py

PC = (255, 215, 0)
BLACK = (108, 98, 113)
WHITE = (250, 250, 250)
BGC = (148, 137, 153)
GREEN = (65, 83, 44)
BC = (198, 192, 199)
BLUE = (113, 145, 160)
RED = (235, 120, 104)
blockSize = 200
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
etatinit = []
s = []

# INIT FONT
py.font.init()
myfont = py.font.SysFont('Comic Sans MS', 50)


def addObstacle(NUM, X, Y):
    obstacles.append((NUM, X, Y))


def Main():
    global SCREEN, CLOCK, WINDOW_WIDTH, WINDOW_HEIGHT, Xmv, Ymv, End
    WINDOW_WIDTH = 590
    WINDOW_HEIGHT = 690
    py.init()
    SCREEN = py.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
    CLOCK = py.time.Clock()
    SCREEN.fill(BGC)

    drawGrid()
    initCORE(1, 1)
    INIT_GRID_NUM()
    py.display.update()

    while True:
        for event in py.event.get():
            if event.type == py.QUIT:
                py.quit()
                sys.exit()
            if event.type == py.MOUSEBUTTONUP:
                ypos, xpos = py.mouse.get_pos()
                # les coordonées
                Cx = int(xpos / 200)
                Cy = int(ypos / 200)
                print(xpos)
                print(ypos)
                if 400 > ypos > 180 and 670 > xpos > 590:
                    list = Creat_list(obstacles.copy())
                    solv = solvable(list)
                    if not solv:
                        print("Non Solvable !")
                        py.time.delay(10)
                    else :
                        print("Solvable !")
                        closed = Astar()
                        path = PATH(closed)
                        py.display.update()
                        DRAW_PATH(path)
                        py.display.update()

                else:
                    MOVE_UP(Cx, Cy)
                    MOVE_DOWN(Cx, Cy)
                    MOVE_LEFT(Cx, Cy)
                    MOVE_RIGHT(Cx, Cy)



def drawGrid():
    for x in range(0, 590, blockSize):
        for y in range(0, 590, blockSize):
            py.draw.rect(SCREEN, BLACK, py.Rect(y + 5, x + 5, 180, 180), 12, 20)


def color_num_block(X, Y, num):
    Xstep = X * 200
    Ystep = Y * 200
    for x in range(0, 590, blockSize):
        for y in range(0, 590, blockSize):
            if Xstep == x and Ystep == y:
                py.draw.rect(SCREEN, BLACK, py.Rect(y + 5, x + 5, 180, 180), 12, 20)
                textsurface = myfont.render(str(num), False, BLACK)
                SCREEN.blit(textsurface, (y + 80, x + 55))


def Creat_list(obstacle):
    list = [(0, 0), (0, 1), (0, 2), (1, 2), (2, 2), (2, 1), (2, 0), (1, 0)]
    listF = [-1, -1, -1, -1, -1, -1, -1, -1]
    for i in range(len(obstacle)):
        N, x, y = obstacle[i]
        index = list.index((x, y))
        listF[index] = N
    return listF


def solvable(liste):
    cpt = 0
    print(liste)
    for i in range(len(liste)):
        for j in range(i + 1, len(liste)):
            if liste[i] > liste[j]:
                cpt += 1
    print(cpt)
    if cpt % 2 == 0:
        return True
    else:
        return False


def INIT_GRID_NUM():
    i = 0
    numbers = [1, 8, 7, 2, 6, 3, 4, 5]
    for x in range(0, 590, blockSize):
        for y in range(0, 590, blockSize):
            if x != 200 or y != 200:
                corx = int(y / 200)
                cory = int(x / 200)
                addObstacle(numbers[i], corx, cory)
                textsurface = myfont.render(str(numbers[i]), False, BLACK)
                SCREEN.blit(textsurface, (x + 80, y + 55))
                i += 1
    textsurface = myfont.render("START", False, BLACK)
    SCREEN.blit(textsurface, (210, 600))


def initCORE(X, Y):
    Xstep = X * 200
    Ystep = Y * 200

    for x in range(0, 590, blockSize):
        for y in range(0, 590, blockSize):
            if Xstep == x and Ystep == y:
                py.draw.rect(SCREEN, BGC, py.Rect(y + 5, x + 5, 180, 180), 12, 20)


def discolor_num_block(X, Y, num):
    Xstep = X * 200
    Ystep = Y * 200

    for x in range(0, 590, blockSize):
        for y in range(0, 590, blockSize):
            if Xstep == x and Ystep == y:
                py.draw.rect(SCREEN, BGC, py.Rect(y + 5, x + 5, 180, 180), 12, 20)
                textsurface = myfont.render(str(num), False, BGC)
                SCREEN.blit(textsurface, (y + 80, x + 55))


def isValid(X, Y):
    if X < 0 or Y < 0 or X >= 3 or Y >= 3:
        return False

    for i in range(len(obstacles)):
        number, x, y = obstacles[i]
        if (x, y) == (X, Y):
            return False

    return True


def MOVE_UP(X, Y):
    adjx = X - 1
    adjy = Y
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))
                discolor_num_block(X, Y, number)
                color_num_block(adjx, adjy, number)
                py.time.delay(100)
                py.display.update()


def MOVE_DOWN(X, Y):
    adjx = X + 1
    adjy = Y
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))
                discolor_num_block(X, Y, number)
                color_num_block(adjx, adjy, number)
                py.time.delay(100)
                py.display.update()


def MOVE_LEFT(X, Y):
    adjx = X
    adjy = Y - 1
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))
                discolor_num_block(X, Y, number)
                color_num_block(adjx, adjy, number)
                py.time.delay(100)
                py.display.update()


def MOVE_RIGHT(X, Y):
    adjx = X
    adjy = Y + 1
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))
                discolor_num_block(X, Y, number)
                color_num_block(adjx, adjy, number)
                py.time.delay(100)
                py.display.update()


def ret_pos_N(N, liste):
    for i in range(len(liste)):
        number, x, y = liste[i]
        if number == N:
            return x, y


def Heur(liste):  # nombre de cases MP
    nbrMP = 0
    parfaite = [(1, 0, 0), (2, 0, 1), (3, 0, 2), (4, 1, 2), (5, 2, 2), (6, 2, 1), (7, 2, 0), (8, 1, 0)]
    for i in range(len(parfaite)):
        N, xp, yp = parfaite[i]
        xo, yo = ret_pos_N(N, liste)
        if xp != xo or yp != yo:
            nbrMP = nbrMP + 1
    return nbrMP


def TrouverVide(liste):
    listexy = []
    for i in range(len(liste)):
        N, x, y = liste[i]
        listexy.append((x, y))

    for i in range(3):
        for j in range(3):
            if (i, j) not in listexy:
                return i, j


def ret_N_coo(X, Y, liste):
    for i in range(len(liste)):
        number, x, y = liste[i]
        if x == X and y == Y:
            return number


def isValid_VV(X, Y):
    if X < 0 or Y < 0 or X >= 3 or Y >= 3:
        return False
    return True


def VoisinageVide(liste):
    listeV = []
    U = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    Xv, Yv = TrouverVide(liste)
    for mv in U:
        x, y = mv
        adjx = Xv + x
        adjy = Yv + y
        if isValid_VV(adjx, adjy):
            N = ret_N_coo(adjx, adjy, liste)
            listeV.append((N, adjx, adjy))
    return listeV


def moveAstar(number, x, y, liste):
    Xv, Yv = TrouverVide(liste)
    liste.append((number, Xv, Yv))
    liste.remove((number, x, y))


def Equal(list1, list2):
    for j in range(len(list1)):
        N, xp, yp = list1[j]
        xo, yo = ret_pos_N(N, list2)
        if xp != xo or yp != yo:
            return False
    return True


def inEqual(liste, listeLISTE1):
    for i in range(len(listeLISTE1)):
        lst, p = listeLISTE1[i]
        if Equal(liste, lst):
            return True
    return False


def Astar():
    # print(obstacles)
    OPEN = []
    CLOSED = []
    G = 0
    OPEN.append((obstacles.copy(), Heur(obstacles.copy()) + G, G, []))
    # print(len(OPEN))
    while len(OPEN) > 0:
        print("-------------------------------------------------------")
        OPEN.sort(key=lambda tup: tup[1])
        State, F, G, p = OPEN[0]
        OPEN.remove(OPEN[0])
        CLOSED.append((State.copy(), p))
        G += 1
        if Heur(State.copy()) == 0:
            break
        else:
            pere = State.copy()
            listeV = VoisinageVide(State)
            while len(listeV) > 0:
                Xv, Yv = TrouverVide(State)
                N, x, y = listeV[0]
                moveAstar(N, x, y, State)
                if inEqual(State, CLOSED):
                    # print("in")
                    moveAstar(N, Xv, Yv, State)
                    listeV.remove(listeV[0])
                    continue
                F = Heur(State.copy()) + G
                OPEN.append((State.copy(), F, G, pere))
                moveAstar(N, Xv, Yv, State)
                listeV.remove(listeV[0])
    return CLOSED


def inEquallst(liste, closed):
    for i in range(len(closed)):
        lst, p = closed[i]
        if Equal(liste, lst):
            return p


def PATH(CLOSED):
    listePath = []
    cpt = len(CLOSED) - 1
    State, P = CLOSED[cpt]
    listePath.append(State)
    listePath.append(P)
    cpt -= 1
    while cpt >= 0:
        State = inEquallst(P, CLOSED)
        if not State:
            break
        listePath.append(State)
        cpt -= 1
        P = listePath[len(listePath) - 1]

    return listePath


def N_diff(list1, list2):
    for j in range(len(list1)):
        N, xp, yp = list1[j]
        xo, yo = ret_pos_N(N, list2)
        if xp != xo or yp != yo:
            return xp, yp


def DRAW_PATH(lisePath):
    lisePath.reverse()
    print(lisePath)
    print(obstacles)
    for i in range(len(lisePath)):
        if not Equal(lisePath[i], obstacles):
            x, y = N_diff(obstacles, lisePath[i])
            MOVE_UP(x, y)
            MOVE_DOWN(x, y)
            MOVE_LEFT(x, y)
            MOVE_RIGHT(x, y)


if __name__ == '__main__':
    Main()
