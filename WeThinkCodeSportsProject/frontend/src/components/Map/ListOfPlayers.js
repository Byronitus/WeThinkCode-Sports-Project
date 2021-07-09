import React, {Component} from "react";
import PropTypes from "prop-types";
import ListOfPlayersInfo from "../Info/ListOfPlayersInfo";

class ListOfPlayers extends Component{

    render() {
        return this.props.playerList.map((league) => (
            <ListOfPlayersInfo user={league} key={league.id}/>
        ));
    }
}

ListOfPlayers.propTypes = {
    playerList:PropTypes.array
}

export default ListOfPlayers;