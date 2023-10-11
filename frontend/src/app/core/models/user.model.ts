import { Role } from './role.model';

export class UserSignin {
  constructor(public username: string, public password: string) {}
}

export class UserRegister {
  constructor(public username: string, public password: string) {}
}

export class User {
  constructor(
    public id: string,
    public username: string,
    public password: string,
    public email: string,
    public firstName: string,
    public lastName: string,
    public address: string,
    public phone: string,
    public city: string,
    public zipCode: string,
    public dateCreated: string,
    public role: Role
  ) {}
}
