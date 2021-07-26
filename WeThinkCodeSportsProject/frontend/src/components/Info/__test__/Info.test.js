import React from "react";
import ReactDOM, {unmountComponentAtNode} from 'react-dom';
import {act} from "@testing-library/react";
import SportInfo from "../SportInfo";
import LeagueInfo from "../LeagueInfo";
import ListOfEventsInfo from "../ListOfEventsInfo";
import ListOfLiveEventsInfo from "../ListOfLiveEventsInfo";
import ListOfPlayersInfo from "../ListOfPlayersInfo";
import LiveScoreInfo from "../LiveScoreInfo";
import SearchInfo from "../SearchInfo";
import TeamListInfo from "../TeamListInfo";

let container;

beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
});

afterEach(() => {
    unmountComponentAtNode(container);
    document.body.removeChild(container);
    container = null;
});

it('SportInfoTest', async () => {
const MockedData = {
    strSport : "MotorSport",
};
global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
    {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<SportInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strSport);
    global.fetch.mockClear();
    delete global.fetch;
});


it('ListOfEventsInfoTest', async () => {
    const MockedData = {
        strLeague : "Formula 1",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<LeagueInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strLeague);
    global.fetch.mockClear();
    delete global.fetch;
});

it('ListOfEventsInfoTest', async () => {
    const MockedData = {
        strEvent : "British Grand Prix",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<ListOfEventsInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strEvent);
    global.fetch.mockClear();
    delete global.fetch;
});

it('ListOfLiveEventsInfoTest', async () => {
    const MockedData = {
        strHomeTeam : "Chelsea",
        strAwayTeam : "Arsenal",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<ListOfLiveEventsInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strHomeTeam+" vs "+MockedData.strAwayTeam);
    global.fetch.mockClear();
    delete global.fetch;
});

it('ListOfPlayersInfoTest', async () => {
    const MockedData = {
        strPlayer : "Lance Stroll",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<ListOfPlayersInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strPlayer);
    global.fetch.mockClear();
    delete global.fetch;
});

it('LiveScoreInfoTest', async () => {
    const MockedData = {
        strHomeTeam : "Arsenal",
        intHomeScore : "0",
        strAwayTeam : "Chelsea",
        intAwayScore : "2",
        strProgress : "49",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<LiveScoreInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strHomeTeam);
    expect(container.textContent).toContain(MockedData.intHomeScore);
    expect(container.textContent).toContain(MockedData.strAwayTeam);
    expect(container.textContent).toContain(MockedData.intAwayScore);
    expect(container.textContent).toContain(MockedData.strProgress+"'");
    global.fetch.mockClear();
    delete global.fetch;
});

it('SearchInfoTest', async () => {
    const MockedData = {
        strTeam : "Aston Martin F1",
        strTeamLogo : "https:\\/\\/www.thesportsdb.com\\/images\\/sports\\/motorsport.jpg",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<SearchInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strTeam);
    global.fetch.mockClear();
    delete global.fetch;
});



it('TeamListInfoTest', async () => {
    const MockedData = {
        strTeam : "Aston Martin F1",
    };
    global.fetch = jest.fn().mockImplementation(() => Promise.resolve(
        {result : () => Promise.resolve(MockedData),}));
    await act(async () => {
        ReactDOM.render(<TeamListInfo user={MockedData} />, container);
    });
    expect(container.textContent).toContain(MockedData.strTeam);
    global.fetch.mockClear();
    delete global.fetch;
});

