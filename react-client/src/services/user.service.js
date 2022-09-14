import axios from 'axios';
import { BehaviorSubject } from 'rxjs';

const API_URL = 'http://localhost/api/user/service/';

const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

class UserService {
  get currentUserValue() {
    return currentUserSubject.value;
  }

  get currentUser() {
    return currentUserSubject.asObservable();
  }

  login(user) {
    const headers = {
      authorization: 'Basic ' + btoa(user.username + ':' + user.password)
    };
    return axios.get(API_URL + 'login', { headers: headers }).then((response) => {
      localStorage.setItem('currentUser', JSON.stringify(response.data));
      currentUserSubject.next(response.data);
    });
  }

  logOut() {
    return axios.post(API_URL + 'logout', {}).then((response) => {
      localStorage.removeItem('currentUser');
      currentUserSubject.next(null);
    });
  }

  register(user) {
    const headers = {
      'Content-Type': 'application/json; charset=UTF-8'
    };
    return axios.post(API_URL + 'register', JSON.stringify(user), { headers: headers });
  }

  users() {
    const headers = {
      'Content-Type': 'application/json; charset=UTF-8'
    };
    return axios.get(API_URL + 'users', { headers: headers });
  }
}

export default new UserService();
