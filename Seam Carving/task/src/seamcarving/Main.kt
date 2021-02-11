package seamcarving

import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.math.pow

private fun Int.toRGB() = intArrayOf(this ushr 16 and 0xff, this ushr 8 and 0xff, this and 0xff)

class ViewImage(private val bufferedImage: BufferedImage) : JFrame() {
    init {
        title = "Seam Carving (Inverse)"
        size = Dimension(bufferedImage.width, bufferedImage.height)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    override fun paint(g: Graphics) {
        g.drawImage(bufferedImage, 0, 0, this)
    }
}

class SeamCarving(inputFileName: String?, outputFileName: String?) {
    private val bufferedImage = ImageIO.read(File(inputFileName!!))

/*
    private val graphics = bufferedImage.graphics
    private fun BufferedImage.inverse() {
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                setRGB(x, y, getRGB(x, y).toRGB().map { 255 - it }.let { (it[0] shl 16) or (it[1] shl 8) or it[2] })
            }
        }
    }
*/

    private fun gradient(x1: Int, y1: Int, x2: Int, y2: Int): Double {
        val (r1, g1, b1) = bufferedImage.getRGB(x1, y1).toRGB()
        val (r2, g2, b2) = bufferedImage.getRGB(x2, y2).toRGB()
        return ((r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2)).toDouble()
    }

    private fun BufferedImage.energy() {
        // DoubleArray[width][height]
        val d = List(width * height) { 0.0 }.chunked(height) { it.toMutableList() }
        (1 until height - 1).forEach { y ->
            (1 until width - 1).forEach { x ->
                // draw all without borders
                d[x][y] = (gradient(x - 1, y, x + 1, y) + gradient(x, y - 1, x, y + 1)).pow(0.5)
            }
            // left border without left/up and left/down corner
            d[0][y] = (gradient(0, y, 2, y) + gradient(0, y - 1, 0, y + 1)).pow(0.5)

            // right border without right/up and right/down corner
            d[width - 1][y] =
                (gradient(width - 1, y, width - 3, y) + gradient(width - 1, y - 1, width - 1, y + 1)).pow(0.5)
        }
        (1 until width - 1).forEach { x ->
            // top border
            d[x][0] = (gradient(x - 1, 0, x + 1, 0) + gradient(x, 0, x, 2)).pow(0.5)

            // bottom border
            d[x][height - 1] =
                (gradient(x - 1, height - 1, x + 1, height - 1) + gradient(x, height - 1, x, height - 3)).pow(0.5)
        }

        // corner left/up
        d[0][0] = (gradient(0, 0, 2, 0) + gradient(0, 0, 0, 2)).pow(0.5)

        // corner right/up
        d[width - 1][0] = (gradient(width - 1, 0, width - 3, 0) + gradient(width - 1, 0, width - 1, 2)).pow(0.5)

        // corner left/down
        d[0][height - 1] = (gradient(0, height - 1, 2, height - 1) + gradient(0, height - 1, 0, height - 3)).pow(0.5)

        // corner right/down
        d[width - 1][height - 1] = (gradient(width - 1, height - 1, width - 3, height - 1) +
                gradient(width - 1, height - 1, width - 1, height - 3)).pow(0.5)

        val maxEnergyValue = d.flatten().maxOf { it }

        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                setRGB(x, y, (255 * d[x][y] / maxEnergyValue).toInt().let { it or (it shl 8) or (it shl 16) })
            }
        }
    }

    init {
        val frame = ViewImage(bufferedImage)
        frame.isVisible = true
        bufferedImage.energy()
        SwingUtilities.updateComponentTreeUI(frame)
        ImageIO.write(bufferedImage, "png", File(outputFileName!!))
    }
}

// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph


class ShortestPath() {
    val v = 9

    fun minDistance(dist: IntArray, sptSet: Array<Boolean?>): Int {
        // Initialize min value
        var min = Int.MAX_VALUE
        var min_index = -1
        for (v in 0 until v) if (sptSet[v] == false && dist[v] <= min) {
            min = dist[v]
            min_index = v
        }
        return min_index
    }

    // A utility function to print the constructed distance array
    fun printSolution(dist: IntArray) {
        println("Vertex \t\t Distance from Source")
        for (i in 0 until v) println(i.toString() + " \t\t " + dist[i])
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    fun dijkstra(graph: Array<IntArray>, src: Int) {
        val dist = IntArray(v)
        // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        val sptSet = arrayOfNulls<Boolean>(v)

        // Initialize all distances as INFINITE and stpSet[] as false
        for (i in 0 until v) {
            dist[i] = Int.MAX_VALUE
            sptSet[i] = false
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0

        // Find shortest path for all vertices
        for (count in 0 until v - 1) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            val u = minDistance(dist, sptSet)

            // Mark the picked vertex as processed
            sptSet[u] = true

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (v in 0 until v)  // Update dist[v] only if is not in sptSet, there is an
            // edge from u to v, and total weight of path from src to
            // v through u is smaller than current value of dist[v]
                if (!sptSet[v]!! && graph[u][v] != 0 && dist[u] != Int.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) dist[v] =
                    dist[u] + graph[u][v]
        }

        // print the constructed distance array
        printSolution(dist)
    }
}

fun main(args: Array<String>) {
//    val inputFileName = Regex("(?:-in )(\\S+)").find(args.joinToString(" "))?.groupValues?.get(1)
//    val outputFileName = Regex("(?:-out )(\\S+)").find(args.joinToString(" "))?.groupValues?.get(1)
//    SeamCarving(inputFileName, outputFileName)

    val graph = arrayOf(
        intArrayOf(0, 4, 0, 0, 0, 0, 0, 8, 0),
        intArrayOf(4, 0, 8, 0, 0, 0, 0, 11, 0),
        intArrayOf(0, 8, 0, 7, 0, 4, 0, 0, 2),
        intArrayOf(0, 0, 7, 0, 9, 14, 0, 0, 0),
        intArrayOf(0, 0, 0, 9, 0, 10, 0, 0, 0),
        intArrayOf(0, 0, 4, 14, 10, 0, 2, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 2, 0, 1, 6),
        intArrayOf(8, 11, 0, 0, 0, 0, 1, 0, 7),
        intArrayOf(0, 0, 2, 0, 0, 0, 6, 7, 0)
    )
    ShortestPath().dijkstra(graph, 0)
}