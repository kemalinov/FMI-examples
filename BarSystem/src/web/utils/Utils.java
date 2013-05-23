package web.utils;


public class Utils {
    
//    public static User UserDtoToUser(UserDTO dto) {
//	return new User(dto.getId(), dto.getName(), dto.getPassword(), RoleDtoToRole(dto.getRoleDto()));
//    }
//    
//    public static Role RoleDtoToRole(RoleDTO dto) {
//	return new Role(dto.getId(), dto.getRole());
//    }
//
//    public static Drink DrinkDtoToDrink(DrinkDTO dto) {
//	return new Drink(dto.getId(), dto.getName(), dto.getIngredients(), dto.getPrice());
//    }
//
//    public static DrinkDTO DrinkToDrinkDto(Drink d) {
//	return new DrinkDTO(d.getId(), d.getName(), d.getIngredients(), d.getPrice());
//    }
//    
//    public static Consumer ConsumerDtoToConsumer(ConsumerDTO dto) {
//	return new Consumer(dto.getConsumerId(), dto.getDate(), dto.getPlace(), UserDtoToUser(dto.getUserId())); 
//    }
//    
//    public static RoleDTO RoleToRoleDto(Role r) {
//	return new RoleDTO(r.getId(), r.getRole());
//    }
//    
//    public static UserDTO UserToUserDto(User u) {
//	return new UserDTO(u.getId(), u.getName(), u.getPassword(), RoleToRoleDto(u.getRole()));
//    } 
//    
//    public static ConsumerDTO ConsumerToConsumerDto(Consumer c) {
//	return new ConsumerDTO(c.getId(), c.getDate(), c.getPlace(), UserToUserDto(c.getUserId())); 
//    }
//    
//    public static Order OrderDtoToOrder(OrderDTO dto) {
//	return new Order(dto.getId(), ConsumerDtoToConsumer(dto.getConsumerId()), DrinkDtoMapToDrinkMap(dto.getDrinks()), dto.getStatus(), dto.getBill());
//    }
//    
//    public static OrderDTO OrderToOrderDto(Order o) {
//	return new OrderDTO(o.getId(),  ConsumerToConsumerDto(o.getConsumerId()), DrinkMapToDrinkDtoMap(o.getDrinks()), o.getStatus(), o.getBill());
//    }
//    
//    public static Map<Drink, Integer> DrinkDtoMapToDrinkMap(Map<DrinkDTO,Integer> dtoMap) {
//	System.out.println("in DrinkDtoMapToDrinkMap "  + dtoMap.size());
//	Map<Drink, Integer> map = new HashMap<Drink, Integer>(dtoMap.size());
//	for(Entry<DrinkDTO, Integer> e : dtoMap.entrySet()) {
//	    Drink drink = DrinkDtoToDrink(e.getKey());
//	    map.put(drink, new Integer(e.getValue()));
//	}
//	System.out.println("in DrinkDtoMapToDrinkMap "  + map.size());
//	return map;
//    }
//
//    public static Map<DrinkDTO, Integer> DrinkMapToDrinkDtoMap(Map<Drink,Integer> map) {
//	Map<DrinkDTO, Integer> dtoMap = new HashMap<DrinkDTO, Integer>(map.size());
//	for(Entry<Drink, Integer> e : map.entrySet()) {
//	    DrinkDTO dDto = DrinkToDrinkDto(e.getKey());
//	    dtoMap.put(dDto, new Integer(e.getValue()));
//	}
//	return dtoMap;
//    }

}
