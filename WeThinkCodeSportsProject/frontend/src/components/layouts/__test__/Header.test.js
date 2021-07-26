import React from "react";
import ReactDOM from 'react-dom';
import Header from '../Header';
import {act} from "@testing-library/react";

let container;

beforeEach(()=>{
    container = document.createElement('div');
    document.body.appendChild(container);
});

afterEach(()=>{
    document.body.removeChild(container);
    container = null;
});

it("Does it render", ()=>{
    act(()=>{
        ReactDOM.render(<Header/>,container);
    });
    expect(container.textContent).toBe("Sports Database");
})