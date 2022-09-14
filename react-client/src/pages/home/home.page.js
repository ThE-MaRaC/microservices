import React from 'react';
import User from '../../models/user';
import UserService from '../../services/user.service';

class HomePage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: new User()
    };
  }

  componentDidMount() {
    UserService.currentUser.subscribe((data) => {
      this.setState({ user: data });
    });
  }

  render() {
    const { user } = this.state;
    return (
      <div className="col-md-12">
        {user && (
          <div className="alert alert-success">
            <strong>Successfull! </strong>
            {user.username}
            <button type="button" className="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
        )}
      </div>
    );
  }
}

export default HomePage;
