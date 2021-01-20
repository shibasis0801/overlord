import {
    Switch,
    Route
} from "react-router-dom";

import Home from "../components/Home/Home";
import React from "react";
import App from "overlord/App";

const Routes = () => (
    <Switch>
        <Route path="/rnw">
            <App />
        </Route>
        <Route path="/users">
            <Home prompt="Hello" defaultName="Patnaik" />
        </Route>
        <Route path="/">
            <Home prompt="I am " defaultName="Shibasis" />
        </Route>
    </Switch>
);

export default Routes;
/*
when you hit url /about

traditional
you hit microsoft.com/tutorials
> GET microsoft.com/tutorials

returns html page
browser takes html as instructions, builds an object from the instructions
This object built by browser by reading html received from server is called DOM
DOM: Document Object Model
where document refers to the html document received by the GET request

Now this is an object. It needs something to run on.
That is the JavaScript VirtualMachine
JS VM runs inside the browser for each website.
Inside that VM, runs the DOM.

2004: AJAX, Gmail -> Asynchronous Javascript and XML -> Name of Tech
XHR (Ajax) -> Name of actual class you use
MS figured that since
    there is a VM,
    there is JS running on that VM,
    the view you see on screen is actually an object called DOM,
    that object is accessible to the VM
You can hence write code to change that DOM object hence affecting what you see on screen.

GMail used this technology to call APIs without having to reload the page.
Why did you originally have to reload?
Error: So that on one call you get the response of all component clicks in the page and when thenuser clicks on the component,
then instead of making another api call, it just reads that part of response and shows it. basically it makes the VM do extra work.

client sends http request
server sends back html page

AJAX: returns API response in XML
generalised
returns what server sends XML, HTML, anything

JS starts becoming famous
JS Objects are defined as
var x = {
    "key": "value"
}
This was very simple compared to XML
Dominated XML, now primary format

SPA: SinglePageApplication
DOM only manipulated through
always return same html for any route

Pros
Super simple server

Cons?
debug
diff?

HTML5 HistoryAPI
Can access, change and detect changes in current route in browser address bar without reload.

ReactRouter abstracts HistoryAPI

dev
front/back
react provides dev server
flask dev server not linked to react as of now.

prod

router(
    switch(
        routes(
            foreach(
                switch(
                    routes(
                    ...

router(history/hash)
    switch
        route1 -> component1
                    component1 ->
                           switch
                                route12
                                route13
                                route14
        route2 -> component2
        route3 -> component3
        .
        .
        .

* */


















