import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import League from "../components/Map/League";
import ListOfListEvents from "../components/Map/ListOfLiveEvents";
import LiveScore from "../components/Map/LiveScore";

export class LiveScoresPage extends Component {

    constructor(props) {
        super(props);
        this.state = {LiveScore:[]}
    }

    componentDidMount() {
        axios.get('/api/LiveScore/'+this.props.location.state.EventID+'/'+this.props.location.state.LeagueID)
            .then(response => this.setState({LiveScore:response.data}))
        this.interval = setInterval(() => axios.get('/api/LiveScore/'+this.props.location.state.EventID+'/'+this.props.location.state.LeagueID)
            .then(response => this.setState({LiveScore:response.data})), 1000);
        // console.log(this.props.location.state.EventID)
        // console.log(this.props.location.state.LeagueID)
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }


    render() {
        if (this.state.LiveScore.length > 0 ) {
            return (
                <div className="container">
                    <Header/>
                    <LiveScore LiveEventList={this.state.LiveScore}/>
                </div>
            );
        }else{
            return(<div>
                Event not live
            </div>);
        }
    }
}

export default LiveScoresPage;