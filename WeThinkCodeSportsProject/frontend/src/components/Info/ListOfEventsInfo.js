import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class ListOfEventsInfo extends Component{

    state ={check : false};

    setCheck = () => {
        this.setState({check:true});
    }

    infoStyle = () =>{
        return {
            backgroundColor: (this.props.user.id %2) == 0? '#c8d4f7cc' :'',
            padding:'10px',
            borderBottom: '1px #ccc dotted',
            display:'flex',
            alignItems:'center',
            justifyContent:'space-between',
            justifyItems: 'flex-start',
        }
    }

    render() {
        if (this.state.check){
            return <Redirect push to={{pathname: "/Events",state: {Description : this.props.user.strDescriptionEN, Video : this.props.user.strVideo , sportType : this.props.user.strSport,
                    Event : this.props.user.strFilename, League : this.props.user.strLeague, Round : this.props.user.intRound,
            Date : this.props.user.dateEvent, Venue : this.props.user.strVenue, Country : this.props.user.strCountry, Started : this.props.user.strStatus
            }}}/>
        }
        const {id} = this.props.user;
        return(
            <div style={this.infoStyle()}>
                <Button color="primary" variant="outlined" onClick={this.setCheck}>
                    {this.props.user.strEvent}
                </Button>
                <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                    <img src={this.props.user.strThumb} alt=""/>
                </div>
            </div>
        );
    }
}

ListOfEventsInfo.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  ListOfEventsInfo;