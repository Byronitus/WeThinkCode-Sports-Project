import React, {Component} from "react";
import PropTypes from "prop-types";
import LiveScoreInfo from "../Info/LiveScoreInfo";

class LiveScore extends Component{

    render() {
        return this.props.LiveEventList.map((LiveScores) =>(
            <LiveScoreInfo user={LiveScores} key={LiveScores.id} />
        ));
    }
}

LiveScore.propTypes = {
    LiveEventList:PropTypes.array.isRequired
}

export default LiveScore;