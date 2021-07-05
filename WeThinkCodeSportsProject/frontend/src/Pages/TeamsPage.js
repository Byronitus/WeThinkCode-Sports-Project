import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import TeamList from "../components/Map/TeamList";

export class TeamsPage extends Component {


    // constructor(props) {
    //     super(props);
    //     this.state = {TeamList:[], SeasonList:[]}
    // }
    //
    //
    // componentDidMount() {
    //     axios.get("/api/team/"+this.props.location.state.sportID)
    //         .then(response => this.setState({TeamList:response.data}))
    //     axios.get("/api/Seasons/"+this.props.location.state.sportID)
    //         .then(response => this.setState({TeamListList : response.data }))
    // }


    render() {
        return (
            <div className="container">
                <Header/>
                {this.props.location.state.TeamDescription}
            </div>
        );
    }
}

export default TeamsPage;