package data;

public class DefaultDescriptions {

  public static final String empty = "{}" ;

  public static final String complete = """
      {
        name: 'collaborativeStateMachine',
        version: '0.1',
        memoryMode: 'distributed',
        stateMachines: [
          {
            name: 'stateMachine1',
            states: [
              {
                name: 'state1',
                entry: [
                  {
                    reference: 'action1'
                  }
                ],
                on: [
                  {
                    target: 'state2',
                    event: 'e1'
                  }
                ]
              },
              {
                name: 'state2',
                entry: [
                  {
                    type: 'assign',
                    variable: 'var',
                    value: 'var+1'
                  }
                ],
                on: [
                  {
                    target: 'state2',
                    event: 'e2'
                  }
                ]
              }
            ],
            actions: [
              {
                name: 'action1',
                type: 'create',
                variable: 'var',
                value: '5'
              }
            ]
          }
        ]
      }
      """;
}
