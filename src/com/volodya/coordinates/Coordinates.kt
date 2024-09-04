package com.volodya.coordinates

import kotlin.math.*

fun doubleToString(value: Double, precision: Int) : String {
    return String.format("%.${precision}f", value)
}

interface Coordinates {
    fun getName() : String
    fun getFields() : Array<Char>
    fun toDecart2d() : Coordinates?
    fun toPolar() : Coordinates?
    fun toDecart3d() : Coordinates?
    fun toCylindrical() : Coordinates?
    fun toSpherical() : Coordinates?
    fun getCoordinates(precision: Int) : String
}

const val DECART2D_NAME = "Двумерные декартовы координаты"
const val POLAR_NAME = "Полярные координаты"
const val DECART3D_NAME = "Трехмерные декартовы координаты"
const val CYLINDRICAL_NAME = "Цилинричнские координаты"
const val SPHERICAL_NAME = "Сферические координаты"

val DECART2D_FIELDS = arrayOf('x', 'y')
val POLAR_FIELDS = arrayOf('р', 'ф')
val DECART3D_FIELDS = arrayOf('x', 'y', 'z')
val CYLINDRICAL_FIELDS = arrayOf('р', 'ф', 'z')
val SPHERICAL_FIELDS = arrayOf('r', '0', 'ф')

class Decart2d(private var x: Double, private var y: Double) : Coordinates {
    override fun getName() : String {
        return DECART2D_NAME
    }
    override fun getFields(): Array<Char> {
        return DECART2D_FIELDS
    }

    fun getX() : Double {
        return x
    }
    fun getY() : Double {
        return x
    }
    fun setCoordinates(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    override fun toDecart2d(): Coordinates? {
        return null
    }
    override fun toPolar(): PolarCoordinates {
        val rho = sqrt(x * x + y * y)
        val phi = atan2(y, x)
        return PolarCoordinates(rho, phi)
    }
    override fun toDecart3d(): Coordinates? {
        return null
    }
    override fun toCylindrical(): Coordinates? {
        return null
    }
    override fun toSpherical(): Coordinates? {
        return null
    }
    override fun getCoordinates(precision: Int) : String {
        return (
                "${DECART2D_FIELDS[0]}: ${doubleToString(x, precision)};\n" +
                "${DECART2D_FIELDS[0]}: ${doubleToString(y, precision)}."
        )
    }
}

class PolarCoordinates(private var rho : Double, private var phi : Double) : Coordinates {
    override fun getName() : String {
        return POLAR_NAME
    }
    override fun getFields(): Array<Char> {
        return POLAR_FIELDS
    }

    fun getRho() : Double {
        return rho
    }
    fun getPhi() : Double {
        return phi
    }
    fun setCoordinates(rho: Double, phi: Double) {
        this.rho = rho
        this.phi = phi
    }

    override fun toDecart2d(): Decart2d {
        val x = rho * cos(phi)
        val y = rho * sin(phi)
        return Decart2d(x, y)
    }
    override fun toPolar(): Coordinates? {
        return null
    }
    override fun toDecart3d(): Coordinates?{
        return null
    }
    override fun toCylindrical(): Coordinates? {
        return null
    }
    override fun toSpherical(): Coordinates? {
        return null
    }
    override fun getCoordinates(precision: Int) : String {
        return (
                "${POLAR_FIELDS[0]}: ${doubleToString(rho, precision)};\n" +
                "${POLAR_FIELDS[1]}: ${doubleToString(phi, precision)}rad."
        )
    }
}

class Decart3d(private var x: Double, private var y: Double, private var z: Double) : Coordinates {
    override fun getName() : String {
        return DECART3D_NAME
    }
    override fun getFields(): Array<Char> {
        return DECART3D_FIELDS
    }

    fun getX() : Double {
        return x
    }
    fun getY() : Double {
        return x
    }
    fun getZ() : Double {
        return x
    }
    fun setCoordinates(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    override fun toDecart2d(): Coordinates? {
        return null
    }
    override fun toPolar(): Coordinates? {
        return null
    }
    override fun toDecart3d(): Coordinates? {
        return null
    }
    override fun toCylindrical(): CylindricalCoordinates {
        val rho = sqrt(x * x + y * y)
        val phi = atan2(y, x)
        return CylindricalCoordinates(rho, phi, z)
    }
    override fun toSpherical(): SphericalCoordinates {
        val r = sqrt(x * x + y * y + z * z)
        val theta = atan(sqrt(x * x + y * y) / z)
        val phi = atan2(y, x)
        return SphericalCoordinates(r, theta, phi)
    }
    override fun getCoordinates(precision: Int) : String {
        return (
                "${DECART3D_FIELDS[0]}: ${doubleToString(x, precision)};\n" +
                "${DECART3D_FIELDS[1]}: ${doubleToString(y, precision)};\n" +
                "${DECART3D_FIELDS[2]}: ${doubleToString(z, precision)}."
        )
    }
}

class CylindricalCoordinates(
    private var rho: Double, private var phi: Double, private var z: Double
) : Coordinates {
    override fun getName() : String {
        return CYLINDRICAL_NAME
    }
    override fun getFields(): Array<Char> {
        return CYLINDRICAL_FIELDS
    }

    fun getRho() : Double {
        return rho
    }
    fun getPhi() : Double {
        return phi
    }
    fun getZ() : Double {
        return z
    }
    fun setCoordinates(rho: Double, phi: Double, z: Double) {
        this.rho = rho
        this.phi = phi
        this.z = z
    }

    override fun toDecart2d(): Coordinates? {
        return null
    }
    override fun toPolar(): Coordinates? {
        return null
    }
    override fun toDecart3d(): Decart3d {
        val x = rho * cos(phi)
        val y = rho * sin(phi)
        return Decart3d(x, y, z)
    }
    override fun toCylindrical(): Coordinates? {
        return null
    }
    override fun toSpherical(): SphericalCoordinates {
        val r = sqrt(rho * rho + z * z)
        val theta = atan2(rho, z)
        return SphericalCoordinates(r, theta, phi)
    }
    override fun getCoordinates(precision: Int): String {
        return (
                "${CYLINDRICAL_FIELDS[0]}: ${doubleToString(rho, precision)};\n" +
                "${CYLINDRICAL_FIELDS[1]}: ${doubleToString(phi, precision)}rad;\n" +
                "${CYLINDRICAL_FIELDS[2]}: ${doubleToString(z, precision)}."
        )
    }

}

class SphericalCoordinates(
    private var r: Double, private var theta: Double, private var phi: Double
) : Coordinates {
    override fun getName() : String {
        return SPHERICAL_NAME
    }
    override fun getFields(): Array<Char> {
        return SPHERICAL_FIELDS
    }

    fun getRadius() : Double {
        return r
    }
    fun getTheta() : Double {
        return theta
    }
    fun getPhi() : Double {
        return phi
    }

    override fun toDecart2d(): Coordinates? {
        return null
    }
    override fun toPolar(): Coordinates? {
        return null
    }
    override fun toDecart3d(): Decart3d {
        val x = r * sin(theta) * cos(phi)
        val y = r * sin(theta) * sin(phi)
        val z = r * cos(theta)
        return Decart3d(x, y, z)
    }
    override fun toCylindrical(): CylindricalCoordinates {
        val rho = r * sin(theta)
        val z = r * cos(theta)
        return CylindricalCoordinates(rho, phi, z)
    }
    override fun toSpherical(): Coordinates? {
        return null
    }
    override fun getCoordinates(precision: Int): String {
        return (
                "${SPHERICAL_FIELDS[0]}: ${doubleToString(r, precision)};\n" +
                "${SPHERICAL_FIELDS[1]}: ${doubleToString(theta, precision)}rad;\n" +
                "${SPHERICAL_FIELDS[2]}: ${doubleToString(phi, precision)}rad."
        )
    }
}