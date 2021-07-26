import React, {Component} from "react";
import PropTypes from "prop-types";
import ListOfLiveEventsInfo from "../Info/ListOfLiveEventsInfo";

class ListOfLiveEvents extends Component{

    render() {
        return this.props.LiveEventList.map((liveEvent) => (
            <ListOfLiveEventsInfo user={liveEvent} key={liveEvent.id}/>
        ));
    }
}

ListOfLiveEvents.propTypes = {
    LiveEventList:PropTypes.array
}

export default ListOfLiveEvents;