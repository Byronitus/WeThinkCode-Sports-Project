import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';



class FixturesInfo extends Component{



    state ={check : false, Description : "", Video : "" , sportType : "",
        Event : "", League : "", Round : "", Date : "", Venue : "", Country : "", Started : ""};



    render() {

        const classes = makeStyles({
            table: {
                minWidth: 650,
            },
        });


        if (this.state.check){
            return <Redirect push to={{pathname: "/Events",state: {Description : this.state.Description, sportType : this.state.sportType,
                    Event : this.state.Event, League : this.state.League, Round : this.state.Round,
                    Date : this.state.Date, Venue : this.state.Venue, Country : this.state.Country, Started : this.state.Started
                }}}/>        }
        if (this.props.user.length > 0) {
            return (
                <TableContainer component={Paper}>
                    <Table className={classes.table} aria-label="simple table" style={{background: "#808080"}}>
                        <TableHead>
                            <TableRow>
                                <TableCell>Name Of Event</TableCell>
                                <TableCell align="right">Date</TableCell>
                                <TableCell align="right">Time</TableCell>

                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {this.props.user.map((fixture) => (
                                <TableRow key={fixture.strEvent} style={{background: "#808080"}}>
                                    <TableCell component="th" scope="row">
                                        <Button color="primary" variant="outlined" onClick={() => {
                                            this.setState({
                                                check: true,
                                                Description: fixture.strDescriptionEN,
                                                Video: fixture.strVideo,
                                                sportType: fixture.strSport,
                                                Event: fixture.strFilename,
                                                League: fixture.strLeague,
                                                Round: fixture.intRound,
                                                Date: fixture.dateEvent,
                                                Venue: fixture.strVenue,
                                                Country: fixture.strCountry,
                                                Started: fixture.strStatus
                                            })
                                        }}>
                                            {fixture.strEvent}
                                        </Button>
                                        {/*{fixture.strHomeTeam} vs {fixture.strAwayTeam}*/}
                                    </TableCell>
                                    <TableCell align="right">{fixture.dateEvent}</TableCell>
                                    <TableCell align="right">{fixture.strTimeLocal}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            );
        }else{
            return (<div> </div>);
        }
    }

}


FixturesInfo.propTypes = {
    user : PropTypes.array
}
export  default  FixturesInfo;