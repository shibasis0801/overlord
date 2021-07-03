package com.shibasispatnaik.pravega.ui

data class Theme(
    val primaryLight: String,
    val primary: String,
    val primaryDark: String,
    val onPrimaryLight: String,
    val onPrimary: String,
    val baseDark: String,
    val base: String,
    val baseLight: String,
    val accentColor: String,
    val onBase: String,
    val onBaseDark: String,
    val separator: String,
    val shadow: String,
)


public val LightTheme = Theme(
    primaryLight = "#009688",
    primary = "#00796B",
    primaryDark = "#00695C",
    onPrimaryLight = "whitesmoke",
    onPrimary = "white",
    baseDark = "white",
    base = "whitesmoke",
    baseLight = "#ccc",
    accentColor = "#72a3c0ff",
    onBase = "#212121",
    onBaseDark = "#757575",
    separator = "#BDBDBD",
    shadow = "rgba(0, 0, 0, 0.14)"
)

public val DarkTheme = Theme(
    primaryLight = "#520050",
    primary = "#520050",
    primaryDark = "#3c273fff",
    onPrimaryLight = "whitesmoke",
    onPrimary = "white",
    baseDark = "#212121",
    base = "#282828",
    baseLight = "#404040",
    accentColor = "#ec98beff",
    onBase = "white",
    onBaseDark = "whitesmoke",
    separator = "#BDBDBD",
    shadow = "rgba(192, 192, 192, 0.14)"
)
