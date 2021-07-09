import React, {Component} from "react";
import PropTypes from "prop-types";
import ListOfEventsInfo from "../Info/ListOfEventsInfo";

class ListOfEvents extends Component{

    render() {
        return this.props.EventList.map((event) =>(
            <ListOfEventsInfo user={event} key={event.id} />
        ));
    }
}

ListOfEvents.propTypes = {
    EventList:PropTypes.array.isRequired
}

export default ListOfEvents;