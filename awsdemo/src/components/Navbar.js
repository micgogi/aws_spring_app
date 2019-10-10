import React, { Component } from 'react'
import {Navbar,Nav,Form,FormControl,Button} from 'react-bootstrap'
import {Link} from 'react-router-dom';


export default class NavBar extends Component {
    render() {
        return (
            <>
            <Navbar bg="dark" variant="dark">
              <Navbar.Brand href="#home">Navbar</Navbar.Brand>
              <Nav className="mr-auto">
               <Nav.Link> <Link to="home">Home</Link></Nav.Link>
               
               <Nav.Link> <Link to="form">Form</Link></Nav.Link>
              </Nav>
              <Form inline>
                <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                <Button variant="outline-info">Search</Button>
              </Form>
            </Navbar>
           
          </>

        )
    }
}


