## Use Case 3

|  Use Case: 3 | Report of population categorized by largest to smallest in of all the capital cities in the world, a continent, and a region. |
|:--------|:------------|
| **Goal in Context** | As an *user*, I want *to produce a report all cities in the world, a continent, a region, country and a district categorized by largest population to smallest* so that I can view population of all the capital cities in different areas easily. <br>|
| **Scope** | The Organisation|
| **Level** | Very High Summary |
|**Preconditions**| We have world.sql database that contain lots of attributes such as countries, cities, continents, languages and populations.|
|**Success End Condition**| Reports that contain populations, categorized by largest to smallest numbers, of all the capital cities in the world, a continent, and a region are produced.|
|**Failed End Condition**| No report is produced.|
|**Primary Actor**| User.|
|**Trigger**| A request from Organisation to produce a report for population of all the cities categorized by largest to smallest population in the world, a continents, and a region.|
|**MAIN SUCCESS SCENARIO**| 1. User requests information of all the capital cities population in various areas. <br><br>2. Population system captures various areas to get populations of all the capital cities in those areas.<br><br>3.Population system extracts populations of all the capital cities based on the targeted areas.<br><br>4. Population system produces all the capital cities in various areas.<br> |
|**EXTENSIONS**| None. |
|**SUB-VARIATIONS**| None. |
|**SCHEDULE**| Release 5.0 |