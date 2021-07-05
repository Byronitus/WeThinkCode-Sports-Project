import React, {Component} from "react";
import LeagueInfo from "../Info/LeagueInfo";
import PropTypes from "prop-types";

class League extends Component{

    render() {
        return this.props.leagueList.map((league) =>(
            <LeagueInfo user={league} key={league.id} />
        ));
    }
}

League.propTypes = {
    leagueList:PropTypes.array.isRequired
}

export default League;