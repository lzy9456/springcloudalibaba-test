package my.dubbo;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import my.dubbo.genricclass.RoleDaoImpl;
import my.dubbo.genricclass.UserDaoImpl;
import my.dubbo.provider.entity.dto.RoleDto;
import my.dubbo.provider.entity.dto.UserDto;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Set;

@Slf4j
public class MainTest {

	public static void main(String[] args) {
//		consStrEqualsTest();

//		String str = String.valueOf(1);


//		isCreatableTest();



		genricClassCovertTest();


	}

	private static void genricClassCovertTest() {
		UserDto dto = new UserDaoImpl().getById(1);
		log.info("{}: {}",dto.getClass().getSimpleName(), JSON.toJSONString(dto));


		RoleDto rdto = new RoleDaoImpl().getById(1);
		log.info("{}: {}",rdto.getClass().getSimpleName(), JSON.toJSONString(rdto));
	}

	private static void consStrEqualsTest() {
		String a = "a";
		String b = "a";
		System.out.println(a==b);
	}



	private static void isCreatableTest() {
		System.out.println(NumberUtils.isCreatable("5.33")); 	//true
		System.out.println(NumberUtils.isCreatable("-5.33"));	//true
		System.out.println(NumberUtils.isCreatable("05.33"));	//true
		System.out.println(NumberUtils.isCreatable("05"));		//true
		System.out.println(NumberUtils.isCreatable("05."));		//true
		System.out.println(NumberUtils.isCreatable("s5"));		//false
	}


	public static void fluentIterableTest() {
		Person person = new Person("person1", 12);
		Person person2 = new Person("person2", 8);
		List<Person> persons = Lists.newArrayList(person,person2);


		// List<Person> 转换为 Set<Person>
		Set<Person> peopleSet = FluentIterable.from(persons).toSet();
		System.out.println(JSON.toJSONString(peopleSet));

		// 过滤年龄大于10
		Iterable<Person> iterable = FluentIterable.from(persons).filter(new Predicate<Person>() {
			@Override
			public boolean apply(Person input) {
				return input.getAge()>=10;
			}
		});
		System.out.println(iterable.iterator().next());


		// List<Person>转换为List<String>
		List<String> lists = FluentIterable.from(persons).transform(new Function<Person, String>() {
			@Override
			public String apply(Person input) {
				return input.getName()+" 年龄"+input.getAge();
			}
		}).toList();

		System.out.println(lists.get(0));
		System.out.println(lists.get(1));
	}


	private static class Person {
		private String name;
		private Integer age;

		public Person(String person1, int i) {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}
}
