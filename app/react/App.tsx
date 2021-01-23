import React, {ReactNode} from 'react';
import {
    FlatList,
    Image,
    SafeAreaView,
    ScrollView,
    SectionList,
    StyleProp,
    StyleSheet,
    Text,
    TextStyle,
    View
} from 'react-native';

import merge from "lodash/merge";
import {DarkTheme, LightTheme, Theme } from "./src/utils/colors";

const isDarkMode = true
const theme = isDarkMode ? DarkTheme : LightTheme;

const styles = StyleSheet.create({
    container: {
        padding: 16,
        backgroundColor: theme.base,
        flex: 1, alignItems: 'center'
    },
    image: {
        width: 200,
        height: 200,
        alignSelf: 'center',
        borderWidth: 4,
        borderRadius: 4,
        borderColor: "gray"
    },
    list: {
        margin: 16,
        width: "80%"
    },
    listItem: {
        marginTop: 16
    }
});

type FunctionalProps = {
    style?: StyleProp<TextStyle>,
    theme?: Theme,
    children: ReactNode
}

function themedText(props: FunctionalProps) {
    // @ts-ignore
    const style = { ...(props.style?.valueOf() ?? {}) };
    console.log(props.style);
    return (
      <Text style={{
          ...style,
          color: props?.theme?.onBase ?? "#fff"
      }}>
          {props.children}
      </Text>
  )
}

const Header = (props: FunctionalProps) => themedText({ ...props, style: { fontSize: 20 } })
const Title = (props: FunctionalProps) => themedText({ ...props, style: { fontSize: 24 } })
const Section = (props: FunctionalProps) => themedText({ ...props, style: { fontSize: 16 } })

// Levels -> (one indexed array)
const skillLevels = [ "", "understand", "build simple", "build proper", "thrive", "contribute", "mentor" ]
const Details = [
    {
        header: "Developer",
        content: [
            { skill: "Android", level: 5 },
            { skill: "Web", level: 4 },
            { skill: "System Design", level: 3 },
            { skill: "Backend", level: 2 },
            { skill: "Algorithms", level: 2 },
            { skill: "Unity", level: 1 }
        ]
    },
    {
        header: "Data Science",
        content: [
            { skill: "Deep Learning", level: 3 },
            { skill: "Machine Learning", level: 2 },
            { skill: "Mathematical Programming", level: 3},
            { skill: "Linear Algebra", level: 2 },
            { skill: "Calculus and Analysis", level: 1  },
            { skill: "Probability and Statistics", level: 1 }
        ]
    },
    {
        header: "Languages",
        content: [
            { skill: "Kotlin", level: 4 },
            { skill: "TypeScript", level: 3 },
            { skill: "C++", level: 3 },
            { skill: "Java", level: 3 },
            { skill: "JavaScript", level: 2 },
            { skill: "Python", level: 2 },
            { skill: "C#", level: 1 }
        ]
    }
]

export class App extends React.Component {
    render() {
        return (
            <SafeAreaView style={styles.container}>
                <ScrollView style={{width: "100%"}}>
                    <Image style={styles.image} source={require("./shibasis.png")} />
                    <Title>Shibasis Patnaik</Title>

                    <View style={styles.list}>
                        {Details.map(detail => (
                            <View style={styles.listItem}>
                                <Header>{detail.header}</Header>
                                {detail.content.map(area => (
                                    <Section>
                                        {`${area.skill} can ${skillLevels[area.level]}`}
                                    </Section>
                                ))}
                            </View>
                        ))}
                    </View>

                    <Header>Reference Skill Levels</Header>
                    <Section>{skillLevels.filter(i => !!i).join("\n")}</Section>
                </ScrollView>
            </SafeAreaView>
        );
    }
}
