import React, {Component} from "react";
import SearchInfo from "../Info/SearchInfo";
import PropTypes from "prop-types";

class TeamResults extends Component{



    render() {
        return this.props.ResultsList.map((search) =>(
            <SearchInfo user={search} key={search.id} />
        ));
    }
}

TeamResults.propTypes = {
    ResultsList:PropTypes.array.isRequired
}

export default TeamResults;