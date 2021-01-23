// Move Color information first to KotlinMultiplatform
export type Theme = {
    primaryLight: string;
    primary: string;
    primaryDark: string;
    onPrimaryLight: string;
    onPrimary: string;
    baseDark: string;
    base: string;
    baseLight: string;
    accentColor: string;
    onBase: string;
    onBaseDark: string;
    separator: string;
    shadow: string;
}


export const LightTheme: Theme = {
    primaryLight: "#009688",
    primary: "#00796B",
    primaryDark: "#00695C",
    onPrimaryLight: "whitesmoke",
    onPrimary: "white",
    baseDark: "white",
    base: "whitesmoke",
    baseLight: "#ccc",
    accentColor: "#72a3c0ff",
    onBase: "#212121",
    onBaseDark: "#757575",
    separator: "#BDBDBD",
    shadow: "rgba(0, 0, 0, 0.14)"
}

export const DarkTheme: Theme = {
    primaryLight: "#520050",
    primary: "#520050",
    primaryDark: "#3c273fff",
    onPrimaryLight: "whitesmoke",
    onPrimary: "white",
    baseDark: "#212121",
    base: "#282828",
    baseLight: "#404040",
    accentColor: "#ec98beff",
    onBase: "white",
    onBaseDark: "whitesmoke",
    separator: "#BDBDBD",
    shadow: "rgba(192, 192, 192, 0.14)"
}
