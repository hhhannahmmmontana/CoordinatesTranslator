import com.volodya.coordinates.*
import java.util.InputMismatchException
import java.util.Scanner

fun printCoordinates(coordinates: Coordinates?, precision: Int) {
    if (coordinates != null) {
        println(coordinates.getName())
        println(coordinates.getCoordinates(precision))
    }
}

fun start() {
    mainLoop@
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
            var intAns: Int
            try {
                intAns = ans.toInt()
            } catch (e: NumberFormatException) {
                continue@mainLoop
            }
            val args = arrayListOf<Double>()
            val fields: Array<Char> = when (intAns) {
                1 -> DECART2D_FIELDS
                2 -> DECART3D_FIELDS
                3 -> POLAR_FIELDS
                4 -> CYLINDRICAL_FIELDS
                5 -> SPHERICAL_FIELDS
                else -> {
                    continue@mainLoop
                }
            }
            print('\n')

            println("Введите координаты:")
            for (i in fields) {
                print("${i}: ")
                try {
                    args.add(scanner.nextDouble())
                } catch (e: InputMismatchException) {
                    continue@mainLoop
                }
            }
            val coordinates: Coordinates = when (intAns) {
                1 -> Decart2d(args[0], args[1])
                2 -> Decart3d(args[0], args[1], args[2])
                3 -> PolarCoordinates(args[0], args[1])
                4 -> CylindricalCoordinates(args[0], args[1], args[2])
                5 -> SphericalCoordinates(args[0], args[1], args[2])
                else -> {
                    continue@mainLoop
                }
            }
            print('\n')

            println("Округление:")
            var precision: Int
            try {
                precision = scanner.nextInt()
            } catch (e: InputMismatchException) {
                continue@mainLoop
            }
            print('\n')

            printCoordinates(coordinates.toDecart2d(), precision)
            printCoordinates(coordinates.toDecart3d(), precision)
            printCoordinates(coordinates.toPolar(), precision)
            printCoordinates(coordinates.toCylindrical(), precision)
            printCoordinates(coordinates.toSpherical(), precision)
            print('\n')
        }
    }
}

fun main(args: Array<String>) {
    start()
}