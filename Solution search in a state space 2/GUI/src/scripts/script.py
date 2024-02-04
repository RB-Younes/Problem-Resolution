import argparse
import os


def parse_args():
    # DEFAULT PATHS TO CHANGE
    parser = argparse.ArgumentParser(description="3x3 puzzle.")
    parser.add_argument('--numb', nargs='?', default="1-8-7-2-6-3-4-5",
                        help='list of numbers')
    return parser.parse_args()


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


def addObstacle(NUM, X, Y):
    obstacles.append((NUM, X, Y))


def Main(numbers):
    INIT_GRID_NUM(numbers)
    list = Creat_list(obstacles.copy())
    solv = solvable(list)
    file_path = os.path.realpath(__file__)
    file_path = file_path[0:len(file_path) - 9]
    if not solv:
        file_object = open(file_path + "Path_result.txt", "w+")
        file_object.write("Non Solvable !")
    else:
        closed = Astar()
        path = PATH(closed)
        file_object = open(file_path + "Path_result.txt", "w+")
        if len(path) > 2:
            for i in range(len(path)):
                if i < len(path) - 1:
                    for j in range(1, 9):
                        x1, y1 = ret_pos_N(j, path[i])
                        x2, y2 = ret_pos_N(j, path[i + 1])
                        if x1 != x2 or y1 != y2:
                            file_object.write("Move " + str(j) + "\n")
        else:
            file_object.write("")


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
    for i in range(len(liste)):
        for j in range(i + 1, len(liste)):
            if liste[i] > liste[j]:
                cpt += 1
    if cpt % 2 == 0:
        return True
    else:
        return False


def INIT_GRID_NUM(numbers):
    i = 0
    for x in range(0, 590, blockSize):
        for y in range(0, 590, blockSize):
            if x != 200 or y != 200:
                corx = int(y / 200)
                cory = int(x / 200)
                addObstacle(numbers[i], corx, cory)
                i += 1


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


def MOVE_DOWN(X, Y):
    adjx = X + 1
    adjy = Y
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))


def MOVE_LEFT(X, Y):
    adjx = X
    adjy = Y - 1
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))


def MOVE_RIGHT(X, Y):
    adjx = X
    adjy = Y + 1
    if isValid(adjx, adjy):
        for i in range(len(obstacles)):
            number, x, y = obstacles[i]
            if (x, y) == (X, Y):
                obstacles.append((number, adjx, adjy))
                obstacles.remove((number, x, y))


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
        # print("-------------------------------------------------------")
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
    listePath.reverse()
    return listePath


if __name__ == '__main__':
    numbers = []

    args = parse_args()
    num = args.numb

    nums = num.split("-")
    for i in range(0, len(nums)):
        numbers.append(int(nums[i]))

    Main(numbers)
