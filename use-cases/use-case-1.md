## Use Case 1

|  Use Case: 1| Report of population categorized by largest to smallest in of all countries in the world, a contient and a region. |
|:--------|:------------|
| **Goal in Context** | As an *user*, I want *to produce a report all the countries in the world categorized by largest population to smallest* so that I can <br>|
| **Scope** | The Organisation|
| **Level** | Very High Summary |
|**Preconditions**| We have world.sql database that contain countries, cities, continents, languages and populations.|
|**Success End Condition**| Reports that contain populations, categorized by largest to smallest numbers, of all countries in the world, a continent and a region are produced.|
|**Failed End Condition**| No report is produced.|
|**Primary Actor**| User.|
|**Trigger**| A request from Organisation to produce a report for population categorized by largest to smallest population in the world, a continents and a region.|
|**MAIN SUCCESS SCENARIO**| 1. User requests information of all countries' population in the world, a continent and a region. <br><br>2. Population system captures world, continents and regions to get populations of each area information.<br><br>3.Population system extracts populations of all countries based on the targeted areas.<br><br>4.Popluation system produces all countries in the world, a continent and a region.<br> |
|**EXTENSIONS**| None. |
|**SUB-VARIATIONS**| None. |
|**SCHEDULE**| Release 5.0 |
