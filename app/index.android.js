import { AppRegistry } from 'react-native';
import packageJson from "./package.json";
import {App} from "./react/App";

AppRegistry.registerComponent(
  packageJson.name,
  () => App
);
