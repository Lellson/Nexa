package data;

public class DefaultDescriptions {

  public static final String empty = "{}" ;

  public static final String complete = """
      {
        name: 'Name',
        version: '0.1',
        memoryMode: "distributed",
        "stateMachines": [
          {
            name: 'Name',
            states: [
              {
                name: 'Name',
                entry: [
                  {
                    type: 'assign',
                    variable: 'Variable',
                    value: 'Value'
                  }
                ],
                on: [
                  {
                    target: 'Name',
                    event: 'e1'
                  }
                ],
                exit: [],
                while: [],
                after: [],
                localContext: {},
                persistentContext: {},
                staticContext: {}
              }
            ],
            localContext: {},
            persistentContext: {},
            guards: [],
            actions: []
          }
        ],
        localContext: {},
        persistentContext: {}
      }
      """;
}
