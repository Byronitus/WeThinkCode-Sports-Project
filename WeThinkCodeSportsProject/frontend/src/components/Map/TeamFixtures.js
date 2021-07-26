import React, {Component} from "react";
import SearchInfo from "../Info/SearchInfo";
import PropTypes from "prop-types";

class TeamFixtures extends Component{



    render() {
        return this.props.FixtureList.map((search) =>(
            <SearchInfo user={search} key={search.id} />
        ));
    }
}

TeamFixtures.propTypes = {
    FixtureList:PropTypes.array.isRequired
}

export default TeamFixtures;