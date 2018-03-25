## Myers 算法

### 1. 问题

#### 输入

两个字符串，一个是旧的字符串，一个是新的字符串。

#### 输出

把两个字符串按照`\n`拆分成以行为单位，得到`oldLines`（长度为`xLength`)，`newLines`(长度为`yLength`)。

从`oldLines`，通过每次删除/增加一行，来得到`newLines`。

每次删除/增加一行为一次操作，寻求最少的操作次数，从`oldLines`得到`newLines`。

#### 例子

| 旧字符串 | 新字符串 |  diff  |
| :--: | :--: | :----: |
|  A   |  B   | -    A |
|  B   |  C   |   B    |
|  C   |  D   |   C    |
|  D   |  A   |   D    |
|      |      | +    A |



### 2. Diff与图搜索

**寻求最少的操作次数**，这个问题很抽象。而这个问题可以被转化为**图搜索**的问题。

![diff graph](https://github.com/ZiheLiu/myers-diff/blob/master/static/diff-graph.png)

如上图所示，其中x轴为`oldLines`，y轴为`newLines`，左上角是(0, 0) ，右下角是(xLength, yLength)。

`graph[i][j]`代表字符串<img src="https://latex.codecogs.com/svg.latex?newLines[0,&space;j)&space;&plus;&space;oldLines[i,&space;xLength)" title="newLines[0, j) + oldLines[i, xLength)" />。例如`graph[0][0]`代表`oldLines[0, xLength)`，即ABCD。`graph[1][1]`代表`newLines[0, 1) + oldLines[1, xLength)`，即BBCD。

所以问题可以转化成，从左上角，如何走到右下角，使得路径长度最短。每一步可以进行如下三种走法。

- 向右走一步，从(i, j) 走到(i + 1, j)。代表减去一行`oldLines[i]`，路径长度为1。
- 向下走一步，从(i, j) 走到(i, j + 1)。代表加上一行`newLines[j]`，路径长度为1。
- 沿着对角线走一步，从(i, j)走到(i + 1, j + 1)。代表减去一行`oldLines[i]`，并且加上一行`newLines[j]`，很明显字符串没有变化，路径长度为0。条件：只能在`newLines[i] == oldLines[j]`时，才可以走对角线。



### 3. 寻找最短路径

#### BFS

使用广度优先搜索，从(0, 0)点开始遍历节点，一直到遍历到(xLength, yLength)为止，当前的深度即为最短路径。广度优先搜索时间复杂度为`O(N + E)`，其中`N`为节点数目，`E`为边的数目。

对于本问题的图，<img src="https://latex.codecogs.com/svg.latex?N&space;=&space;xLength&space;*&space;yLength" title="N = xLength * yLength" />, <img src="https://latex.codecogs.com/svg.latex?E&space;=&space;xLength&space;*&space;yLength&space;*&space;min(xLength&space;&plus;&space;yLength)" title="E = xLength * yLength * min(xLength + yLength)" />（每个节点可以向下或向右走一步，或者是可走对角线上的点，最坏情况下当前节点与整条对角线每个点都有长度为0的边，即<img src="https://latex.codecogs.com/svg.latex?min(xLength&space;&plus;&space;yLength)" title="min(xLength + yLength)" />条边)。时间复杂度为<img src="https://latex.codecogs.com/svg.latex?$$O(n^3)$$" title="$$O(n^3)$$" />。

#### Myers



