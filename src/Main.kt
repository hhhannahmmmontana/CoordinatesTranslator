import com.volodya.coordinates.*
import java.util.Scanner

fun printCoordinates(coordinates: Pair<Boolean, Coordinates>, precision: Int) {
    if (!coordinates.first) {
        println(coordinates.second.getName())
        println(coordinates.second.getCoordinates(precision))
    }
}

fun start() {
    while (true) {
        print(
            "Выберите исходную систему координат:\n" +
            "\t1. ${DECART2D_NAME}\n" +
            "\t2. ${DECART3D_NAME}\n" +
            "\t3. ${POLAR_NAME}\n" +
            "\t4. ${CYLINDRICAL_NAME}\n" +
            "\t5. ${SPHERICAL_NAME}\n\n" +
            "q - Выход\n"
        )
        val scanner = Scanner(System.`in`)
        val ans = scanner.next()
        if (ans == "q") {
            break
        } else {
            val intAns = ans.toInt()
            val args = arrayListOf<Double>()
            val fields: Array<Char> = when (intAns) {
                1 -> DECART2D_FIELDS
                2 -> DECART3D_FIELDS
                3 -> POLAR_FIELDS
                4 -> CYLINDRICAL_FIELDS
                5 -> SPHERICAL_FIELDS
                else -> {
                    continue
                }
            }
            print('\n')

            println("Введите координаты:")
            for (i in fields) {
                print("${i}: ")
                args.add(scanner.nextDouble())
            }
            val coordinates: Coordinates = when (intAns) {
                1 -> Decart2d(args[0], args[1])
                2 -> Decart3d(args[0], args[1], args[2])
                3 -> PolarCoordinates(args[0], args[1])
                4 -> CylindricalCoordinates(args[0], args[1], args[2])
                5 -> SphericalCoordinates(args[0], args[1], args[2])
                else -> {
                    continue
                }
            }
            print('\n')

            println("Округление:")
            val precision: Int = scanner.nextInt()
            print('\n')

            var tempCoordinates: Pair<Boolean, Coordinates>
            if (intAns != 1) {
                tempCoordinates = coordinates.toDecart2d()
                printCoordinates(tempCoordinates, precision)
            }
            if (intAns != 2) {
                tempCoordinates = coordinates.toDecart3d()
                printCoordinates(tempCoordinates, precision)
            }
            if (intAns != 3) {
                tempCoordinates = coordinates.toPolar()
                printCoordinates(tempCoordinates, precision)
            }
            if (intAns != 4) {
                tempCoordinates = coordinates.toCylindrical()
                printCoordinates(tempCoordinates, precision)
            }
            if (intAns != 5) {
                tempCoordinates = coordinates.toSpherical()
                printCoordinates(tempCoordinates, precision)
            }
            print('\n')
        }
    }
}

fun main(args: Array<String>) {
    start()
}