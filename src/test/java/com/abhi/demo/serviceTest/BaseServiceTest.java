package com.abhi.demo.serviceTest;

import com.abhi.demo.CoreConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
@SpringBootTest
@Transactional
public abstract class BaseServiceTest {


}
