import React, {Component} from "react";
import PropTypes from "prop-types";
import SeasonInfo from "../Info/SeasonInfo";

class Season extends Component{



    render() {
        return this.props.SeasonList.map((season) =>(
            <SeasonInfo user={season} key={season.id} LeagueId={this.props.LeagueId}/>
        ));
    }
}

Season.propTypes = {
    SeasonList:PropTypes.array.isRequired,
    LeagueId:PropTypes.any.isRequired
}

export default Season;