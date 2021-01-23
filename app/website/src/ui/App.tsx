import React from 'react';
import Routes from './common/Routes';
import {BrowserRouter} from "react-router-dom";
import Container from "./common/Container";

const App = () => (
  <>
    <BrowserRouter>
        <Container>
            <Routes/>
        </Container>
    </BrowserRouter>
  </>
)

export default App;
