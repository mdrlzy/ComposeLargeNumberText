package com.mdrlzy.largenumbertext

import java.math.BigDecimal

internal enum class Scale(val symbol: String, val value: BigDecimal) {
    ONE("", BigDecimal(1)),
    KILO("k", BigDecimal(10).pow(3)),
    MEGA("M", BigDecimal(10).pow(6)),
    GIGA("G", BigDecimal(10).pow(9)),
    TERA("T", BigDecimal(10).pow(12)),
    PETA("P", BigDecimal(10).pow(15)),
    EXA("E", BigDecimal(10).pow(18)),
    ZETTA("Z", BigDecimal(10).pow(21)),
    YOTTA("Y", BigDecimal(10).pow(24)),
    RONNA("R", BigDecimal(10).pow(27)),
    QUETTA("Q", BigDecimal(10).pow(30)),
}

internal fun Scale.next() =
    when (this) {
        Scale.ONE -> Scale.KILO
        Scale.KILO -> Scale.MEGA
        Scale.MEGA -> Scale.GIGA
        Scale.GIGA -> Scale.TERA
        Scale.TERA -> Scale.PETA
        Scale.PETA -> Scale.EXA
        Scale.EXA -> Scale.ZETTA
        Scale.ZETTA -> Scale.YOTTA
        Scale.YOTTA -> Scale.RONNA
        Scale.RONNA -> Scale.QUETTA
        Scale.QUETTA -> error("QUETTA is last scale, use it")
    }