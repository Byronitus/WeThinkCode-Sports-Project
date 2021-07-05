import React, {Component} from 'react';
import SportInfo from "../Info/SportInfo";
import PropTypes from "prop-types";

class Sports extends Component{

    render() {
        return this.props.sports.map((sport) =>(
            <SportInfo user={sport} key={sport.id} />
        ));
    }
}

//PropTypes
Sports.propTypes = {
    sports:PropTypes.array.isRequired
}
export default Sports;