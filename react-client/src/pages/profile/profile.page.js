import React from 'react';
import UserService from '../../services/user.service';

class ProfilePage extends React.Component {
  constructor(props) {
    super(props);

    if (!UserService.currentUserValue) {
      this.props.history.push('/');
      return;
    }

    this.state = {
      user: UserService.currentUserValue,
      users: []
    };
  }

  componentDidMount() {
    this.setState({
      users: { loading: true }
    });
    UserService.users().then(
      (users) => {
        this.setState({ users: users.data });
      },
      (error) => {
        if (error.response.status === 401) {
          UserService.logOut().then((data) => {
            this.props.history.push('/');
          });
        }
      }
    );
  }

  render() {
    const { users } = this.state;
    return (
      <div className="col-md-12">
        <div className="jumbotron">
          <h1 className="display-4">Hello, {this.state.user.name}</h1>
        </div>
        {users.loading && <em>Loading users...</em>}
        {users.length && (
          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Username</th>
                <th scope="col">Role</th>
                <th scope="col">Email</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user, index) => (
                <tr key={user.id}>
                  <th scope="row">{index + 1}</th>
                  <td>{user.name}</td>
                  <td>{user.username}</td>
                  <td>{user.role}</td>
                  <td>{user.email}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    );
  }
}

export default ProfilePage;
