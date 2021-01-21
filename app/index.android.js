import { AppRegistry } from 'react-native';
import packageJson from "./package.json";
import {App} from "./react/App";

const isHermes = () => !!global.HermesInternal;

AppRegistry.registerComponent(
  packageJson.name,
  () => App
);
