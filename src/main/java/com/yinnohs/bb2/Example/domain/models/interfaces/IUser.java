package com.yinnohs.bb2.Example.domain.models.interfaces;

import java.time.LocalDate;

public interface IUser {
      Long  userId = null;
      String name = "";

      String surname = "";

      String email = "";

      String password = "";

     LocalDate creationDate = null;

     Boolean isDeleted = false;

}
