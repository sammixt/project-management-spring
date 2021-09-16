package com.sammixt.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sammixt.pma.entites.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long> {

}
