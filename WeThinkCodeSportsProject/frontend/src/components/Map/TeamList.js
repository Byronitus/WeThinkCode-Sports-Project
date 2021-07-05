import React, {Component} from "react";
import PropTypes from "prop-types";
import TeamListInfo from "../Info/TeamListInfo";

class TeamList extends Component{

    render() {
        return this.props.teams.map((team) =>(
            <TeamListInfo user={team} key={team.id} />
        ));
    }
}

//PropTypes
TeamList.propTypes = {
    teams:PropTypes.array.isRequired
}
export default TeamList;